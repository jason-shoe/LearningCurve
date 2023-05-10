import React, { useCallback } from "react";
import { useRouter } from "next/router";
import { useAuth } from "../lib/AuthUserProvider";
import { firebaseAuth } from "../lib/Firebase";
import {
  GoogleAuthProvider,
  signInWithPopup,
  createUserWithEmailAndPassword,
} from "firebase/auth";
import { AuthInput, AuthInputType } from "../lib/AuthInput";

export const Signup = React.memo(function SignupFn() {
  const router = useRouter();
  const { authUser } = useAuth();

  const signUpWithGoogle = useCallback(() => {
    const provider = new GoogleAuthProvider();
    signInWithPopup(firebaseAuth, provider)
      .then(() => router.push("/"))
      .catch(console.error);
  }, []);

  const submitEmailAndPassword = useCallback(
    (email: string, password: string) => {
      createUserWithEmailAndPassword(firebaseAuth, email, password).then(() =>
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
        inputType={AuthInputType.SIGN_UP}
        onClickGoogle={signUpWithGoogle}
        onSubmitEmailAndPassword={submitEmailAndPassword}
      />
    </div>
  );
});

export default Signup;
