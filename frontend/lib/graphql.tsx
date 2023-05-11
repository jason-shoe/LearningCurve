import { gql, GraphQLClient } from "graphql-request";
import {
  CreateUser,
  Mutation,
  MutationCreateUserArgs,
  Query,
  QueryTextByIdArgs,
  Text,
} from "../graphqlTypes";

const getGraphqlClient = (tokenId: string | undefined) => {
  const graphqlClient = new GraphQLClient("http://localhost:8080/graphql", {
    headers: tokenId ? { Authorization: `Bearer ${tokenId}` } : {},
  });

  return graphqlClient;
};

export type GetAllTextsResponse = Pick<Text, "id" | "title" | "text">[];
async function getAllTexts(tokenId: string): Promise<GetAllTextsResponse> {
  const graphqlClient = getGraphqlClient(tokenId);
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

async function getFullText(tokenId: string | undefined, textId: string) {
  const graphqlClient = getGraphqlClient(tokenId);
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

async function createUser(tokenId: string | undefined, request: CreateUser) {
  const graphqlClient = getGraphqlClient(tokenId);
  const query = gql`
    mutation CreateUser($request: CreateUser!) {
      createUser(request: $request)
    }
  `;
  const response = await graphqlClient.request<
    Mutation,
    MutationCreateUserArgs
  >(query, { request });
}

const GraphqlService = { getFullText, getAllTexts, createUser };

export default GraphqlService;
