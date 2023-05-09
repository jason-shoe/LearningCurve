import { gql, GraphQLClient } from "graphql-request";
import { Query, QueryTextByIdArgs } from "../graphqlTypes";

const graphqlClient = new GraphQLClient("http://localhost:8080/graphql", {});

async function getFullText(textId: string) {
  const query = gql`
    query TextById($id: ID!) {
      textById(id: $id) {
        id
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

const GraphqlService = { getFullText };

export default GraphqlService;
