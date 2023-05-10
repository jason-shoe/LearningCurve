// generate me a login component that uses GoogleAuthUserProvider and firebase email authentication

import React, { useCallback, useState } from "react";
import { useRouter } from "next/router";
import { useAuth } from "../lib/AuthUserProvider";
import { firebaseAuth } from "../lib/Firebase";
import {
  GoogleAuthProvider,
  signInWithPopup,
  signInWithEmailAndPassword,
} from "firebase/auth";
import { AuthInput, AuthInputType } from "../lib/AuthInput";

export const Login = React.memo(function LoginFn() {
  const router = useRouter();
  const { authUser } = useAuth();

  const signInWithGoogle = useCallback(() => {
    const provider = new GoogleAuthProvider();
    signInWithPopup(firebaseAuth, provider)
      .then(() => router.push("/"))
      .catch(console.error);
  }, []);

  const submitEmailAndPassword = useCallback(
    (email: string, password: string) => {
      signInWithEmailAndPassword(firebaseAuth, email, password).then(() =>
        router.push("/").catch(console.error)
      );
    },
    []
  );

  if (authUser != null) {
    router.push("/");
    return null;
  }

  return (
    <div>
      <AuthInput
        inputType={AuthInputType.LOGIN}
        onClickGoogle={signInWithGoogle}
        onSubmitEmailAndPassword={submitEmailAndPassword}
      />
    </div>
  );
});

export default Login;
