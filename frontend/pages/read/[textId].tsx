import styles from "../../styles/ReadTextId.module.scss";
import { GetServerSidePropsContext } from "next";
import React, { useMemo } from "react";
import { Text } from "../../graphqlTypes";
import CookieStorage from "../../lib/CookieStorage";
import GraphqlService from "../../lib/graphql";
import { Phrase } from "../../graphqlTypes";

interface NonPhrase {
  value: string;
}

export function isPhrase(
  maybeNonPhrase: Phrase | NonPhrase
): maybeNonPhrase is Phrase {
  return "id" in maybeNonPhrase;
}
export const ReadText = React.memo(function ReadTextFn({
  text,
}: {
  text?: Text;
}) {
  const [exposedPhraseIds, setExposedPhraseIds] = React.useState<string[]>([]);
  const textToPhraseMap = useMemo(() => {
    const textToPhraseMap: { [key: string]: Phrase } = {};

    if (text?.phrases == null) {
      return textToPhraseMap;
    }

    for (const phrase of text.phrases) {
      textToPhraseMap[phrase.value] = phrase;
    }

    return textToPhraseMap;
  }, []);

  const phrases = useMemo(() => {
    const phrases: (Phrase | NonPhrase)[] = [];
    if (text == null) {
      return phrases;
    }
    for (const character of text.text) {
      // try to add another character
      const lastPhrase = phrases[phrases.length - 1];

      if (lastPhrase == null) {
        phrases.push({ value: character });
        continue;
      }

      // if you can extend the last phrase, do it
      if (lastPhrase.value + character in textToPhraseMap) {
        phrases[phrases.length - 1] =
          textToPhraseMap[lastPhrase.value + character];
        continue;
      }

      if (character in textToPhraseMap) {
        phrases.push(textToPhraseMap[character]);
        continue;
      }

      if (!isPhrase(lastPhrase)) {
        // TODO check if you can match a phrase starting fromt eh end
        const newPhraseValue = lastPhrase.value + character;
        let couldParse = false;
        for (let i = 0; i < newPhraseValue.length; i++) {
          const subPhrase = newPhraseValue.substring(i);
          if (subPhrase in textToPhraseMap) {
            phrases[phrases.length - 1] = {
              value: newPhraseValue.substring(0, i),
            };
            phrases.push(textToPhraseMap[subPhrase]);
            couldParse = true;
            break;
          }

          // if you can't extend the last phrase, but the last phrase is a non-phrase, extend it
          if (!couldParse) {
            phrases[phrases.length - 1] = { value: newPhraseValue };
          }
          continue;
        }
      } else {
        phrases.push({ value: character });
      }
    }
    return phrases;
  }, []);

  return (
    <div className={styles.container}>
      {phrases.map((phrase: Phrase | NonPhrase) => (
        <div
          className={
            isPhrase(phrase)
              ? exposedPhraseIds.includes(phrase.id)
                ? styles.underline
                : styles.phrase
              : styles.container
          }
        >
          {phrase.value}
        </div>
      ))}
    </div>
  );
});

export async function getServerSideProps(context: GetServerSidePropsContext) {
  const { textId } = context.params || {};
  const tokenId = CookieStorage.getTokenId(context);
  let text: Text | undefined;
  if (typeof textId === "string") {
    text = await GraphqlService.getFullText(tokenId, textId);
  }
  return {
    props: {
      text,
    },
  };
}

export default ReadText;
