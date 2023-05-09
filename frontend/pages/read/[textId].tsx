import { GetServerSidePropsContext } from "next";
import React from "react";
import { Query, QueryTextByIdArgs, Text } from "../../graphqlTypes";
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
  let text: Text | undefined;
  if (typeof textId === "string") {
    text = await GraphqlService.getFullText(textId);
  }
  return {
    props: {
      text,
    },
  };
}

export default ReadText;
