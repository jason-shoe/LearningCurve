// make me an app header that has signup and login buttons or a logout button
import React from "react";
import { useRouter } from "next/router";
import { useAuth } from "../lib/AuthUserProvider";
import { firebaseAuth } from "../lib/Firebase";
import { signOut } from "firebase/auth";
import styles from "../styles/AppHeader.module.scss";

export const AppHeader = React.memo(function AppHeaderFn() {
  const { authUser } = useAuth();
  const router = useRouter();
  return (
    <div className={styles.container}>
      {authUser != null ? (
        <button
          onClick={async () => {
            const auth = firebaseAuth;
            try {
              await signOut(auth);
              router.push("/");
            } catch (e) {
              console.error(e);
            }
          }}
        >
          logout
        </button>
      ) : (
        <div>
          <button
            onClick={() => {
              router.push("/login");
            }}
          >
            Login
          </button>
          <button
            onClick={() => {
              router.push("/signup");
            }}
          >
            Signup
          </button>
        </div>
      )}
    </div>
  );
});

export default AppHeader;
