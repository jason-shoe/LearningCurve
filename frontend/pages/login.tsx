// generate me a login component that uses GoogleAuthUserProvider and firebase email authentication

import React from "react";
import { useRouter } from "next/router";
import { useAuth } from "../lib/AuthUserProvider";
import { firebaseAuth } from "../lib/Firebase";
import { GoogleAuthProvider, signInWithPopup } from "firebase/auth";

export const Login = React.memo(function LoginFn() {
  const router = useRouter();
  const { authUser } = useAuth();
  if (authUser != null) {
    router.push("/");
    return null;
  }
  return (
    <div>
      <button
        onClick={async () => {
          const auth = firebaseAuth;
          const provider = new GoogleAuthProvider();
          try {
            await signInWithPopup(auth, provider);
            router.push("/");
          } catch (e) {
            console.error(e);
          }
        }}
      >
        login with google
      </button>
    </div>
  );
});

export default Login;
