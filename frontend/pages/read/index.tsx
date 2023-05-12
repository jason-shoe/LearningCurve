import { GetServerSideProps, GetServerSidePropsContext } from "next";
import { useRouter } from "next/router";
import React, { useCallback } from "react";
import GraphqlService, { GetAllTextsResponse } from "../../lib/graphql";
import { Text } from "../../graphqlTypes";
import styles from "../../styles/Read.module.scss";
import cookie from "cookie";
import CookieStorage from "../../lib/CookieStorage";

export const Read = React.memo(function ReadFn({
  texts,
  cookies,
}: {
  texts: GetAllTextsResponse;
  cookies: Partial<{
    [key: string]: string;
  }>;
}) {
  console.log("cookies", cookies);
  return (
    <div className={styles.container}>
      <table>
        <thead>
          <tr>
            <th>id</th>
            <th>title</th>
          </tr>
        </thead>
        <tbody>
          {texts.map((text) => (
            <ReadRow text={text} />
          ))}
        </tbody>
      </table>
    </div>
  );
});

export const ReadRow = React.memo(function ReadRowFn({
  text,
}: {
  text: Pick<Text, "id" | "title" | "text">;
}) {
  const router = useRouter();

  const onClickText = useCallback(() => {
    router.push(router.asPath + "/" + text.id);
  }, []);

  return (
    <tr onClick={onClickText}>
      <td>{text.id}</td>
      <td>{text.title}</td>
    </tr>
  );
});

export async function getServerSideProps(context: GetServerSidePropsContext) {
  const tokenId = CookieStorage.getTokenId(context);
  const texts = await GraphqlService.getAllTexts(tokenId);
  return { props: { texts, cookies: context.req.cookies } };
}

export default Read;
