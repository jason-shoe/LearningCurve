import { GetServerSidePropsContext } from "next";
import Cookies from "js-cookie";
import cookie from "cookie";

export function setTokenId(value: string) {
  Cookies.set("tokenId", value, { expires: 1 });
}

export function getTokenId(
  context: GetServerSidePropsContext
): string | undefined {
  return cookie.parse(context.req.headers.cookie || "").tokenId;
}

const CookieStorage = { setTokenId, getTokenId };

export default CookieStorage;
