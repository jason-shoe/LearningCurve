import { GetServerSidePropsContext } from "next";
import React from "react";
import { Text } from "../../graphqlTypes";
import CookieStorage from "../../lib/CookieStorage";
import GraphqlService from "../../lib/graphql";

export const ReadText = React.memo(function ReadTextFn({
  text,
}: {
  text?: Text;
}) {
  return <div>{text?.text}</div>;
});

export async function getServerSideProps(context: GetServerSidePropsContext) {
  const { textId } = context.params || {};
  const tokenId = CookieStorage.getCookie(context, "token");
  let text: Text | undefined;
  if (typeof textId === "string") {
    text = await GraphqlService.getFullText(tokenId, textId);
  }
  return {
    props: {
      text,
      tokenId,
    },
  };
}

export default ReadText;
