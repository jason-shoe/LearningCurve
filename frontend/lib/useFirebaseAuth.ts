import { useEffect, useState } from "react";
import { User } from "@firebase/auth";
import { firebaseAuth } from "./Firebase";

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

  const authStateChanged = async (authState: User | null) => {
    if (!authState) {
      setAuthUser(null);
      setLoading(false);
      return;
    }

    setLoading(true);
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
