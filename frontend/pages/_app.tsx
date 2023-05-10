import "../styles/globals.css";
import type { AppProps } from "next/app";
import { AuthUserProvider } from "../lib/AuthUserProvider";
import AppHeader from "../lib/AppHeader";
import { AppContainer } from "../lib/AppContainer";

export default function App({ Component, pageProps }: AppProps) {
  return (
    <AuthUserProvider>
      <AppHeader />
      <AppContainer>
        <Component {...pageProps} />
      </AppContainer>
    </AuthUserProvider>
  );
}
