import { gql, GraphQLClient } from "graphql-request";
import { Query, QueryTextByIdArgs, Text } from "../graphqlTypes";

const graphqlClient = new GraphQLClient("http://localhost:8080/graphql", {});

export type GetAllTextsResponse = Pick<Text, "id" | "title" | "text">[];
async function getAllTexts(): Promise<GetAllTextsResponse> {
  const query = gql`
    query AllTexts {
      texts {
        id
        text
        title
      }
    }
  `;

  const response = await graphqlClient.request<Query>(query);
  return response.texts;
}

async function getFullText(textId: string) {
  const query = gql`
    query TextById($id: ID!) {
      textById(id: $id) {
        id
        title
        text
        translation
        phrases {
          id
        }
        author {
          id
          name
        }
      }
    }
  `;

  const response = await graphqlClient.request<Query, QueryTextByIdArgs>(
    query,
    {
      id: textId,
    }
  );
  return response.textById ?? undefined;
}

const GraphqlService = { getFullText, getAllTexts };

export default GraphqlService;
