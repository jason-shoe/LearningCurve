import { GetServerSideProps } from "next";
import { useRouter } from "next/router";
import React, { useCallback } from "react";
import GraphqlService, { GetAllTextsResponse } from "../../lib/graphql";
import { Text } from "../../graphqlTypes";
import styles from "../../styles/Read.module.scss";

export const Read = React.memo(function ReadFn({
  texts,
}: {
  texts: GetAllTextsResponse;
}) {
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

export async function getServerSideProps() {
  const texts = await GraphqlService.getAllTexts();
  return { props: { texts } };
}

export default Read;
