import React, { createContext, useContext } from "react";
import { AuthUser, useFirebaseAuth } from "./useFirebaseAuth";

type AuthContextType = {
  authUser: AuthUser | null;
  loading: boolean;
};

const AuthUserContext = createContext<AuthContextType>({
  authUser: null,
  loading: true,
});

export function AuthUserProvider({ children }: { children: React.ReactNode }) {
  const auth = useFirebaseAuth();
  return (
    <AuthUserContext.Provider value={auth}>{children}</AuthUserContext.Provider>
  );
}

export const useAuth = (): AuthContextType => useContext(AuthUserContext);
