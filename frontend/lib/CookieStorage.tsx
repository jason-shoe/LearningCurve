import { GetServerSidePropsContext } from "next";

export function setCookie(key: string, value: string) {
  const newKeyValueMap = cookieToMap();
  newKeyValueMap[key] = value;
  mapToCookies(newKeyValueMap);
}

export function getCookie(
  context: GetServerSidePropsContext,
  key: string
): string | undefined {
  const cookie = context.req.headers.cookie;
  return cookieToMap(cookie)[key];
}

function cookieToMap(cookie?: string) {
  const cookies = (cookie ?? document.cookie).split(";");
  const keyValues: { [key: string]: string } = {};
  for (const cookie of cookies) {
    const splitKeyValue = cookie.split("=");
    if (splitKeyValue.length === 2) {
      keyValues[splitKeyValue[0]] = splitKeyValue[1];
    }
  }
  return keyValues;
}

function mapToCookies(keyValue: { [key: string]: string }) {
  let stringCookie = "";
  for (const [key, value] of Object.entries(keyValue)) {
    stringCookie += `${key}=${value};`;
  }
  document.cookie = stringCookie;
}

const CookieStorage = { setCookie, getCookie };

export default CookieStorage;
