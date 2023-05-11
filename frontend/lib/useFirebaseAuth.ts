import { useEffect, useState } from "react";
import { User } from "@firebase/auth";
import { firebaseAuth } from "./Firebase";
import { useRouter } from "next/router";
import CookieStorage from "./CookieStorage";

export interface AuthUser {
  uid: string;
  email: string;
}

const formatAuthUser = (user: User): AuthUser => ({
  uid: user.uid,
  email: user.email!,
});

export function useFirebaseAuth() {
  const [authUser, setAuthUser] = useState<AuthUser | null>(null);
  const [loading, setLoading] = useState(true);
  const router = useRouter();

  const authStateChanged = async (authState: User | null) => {
    if (!authState) {
      setAuthUser(null);
      // router.push("/login");
      setLoading(false);
      return;
    }

    setLoading(true);
    const token = await authState.getIdToken();
    CookieStorage.setCookie("token", token);
    var formattedUser = formatAuthUser(authState);
    setAuthUser(formattedUser);
    setLoading(false);
  };

  useEffect(() => {
    const unsubscribe = firebaseAuth.onAuthStateChanged(authStateChanged);
    return () => unsubscribe();
  }, []);

  return {
    authUser,
    loading,
  };
}
