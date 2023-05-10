import styles from "../styles/AuthInput.module.scss";
import React, { useCallback, useState } from "react";

export const AuthInputType = {
  SIGN_UP: "SIGN_UP",
  LOGIN: "LOGIN",
} as const;

export type AuthInputType = keyof typeof AuthInputType;

export const AuthInput = React.memo(function AuthInputFn({
  inputType,
  onClickGoogle,
  onSubmitEmailAndPassword,
}: {
  inputType: AuthInputType;
  onClickGoogle: () => void;
  onSubmitEmailAndPassword: (email: string, password: string) => void;
}) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const handleEmailChange = useCallback(
    (e: React.ChangeEvent<HTMLInputElement>) => {
      setEmail(e.target.value);
    },
    []
  );

  const handlePasswordChange = useCallback(
    (e: React.ChangeEvent<HTMLInputElement>) => {
      setPassword(e.target.value);
    },
    []
  );

  const handleSubmitEmailAndPassword = useCallback(
    () => onSubmitEmailAndPassword(email, password),
    [email, password]
  );

  const label = inputType === AuthInputType.LOGIN ? "Login" : "Sign up";

  return (
    <div className={styles.container}>
      <h2>{label}</h2>
      <div className={styles.emailContainer}>
        <div className={styles.formRow}>
          <label>Email</label>
          <input
            placeholder="Email"
            value={email}
            onChange={handleEmailChange}
          />
        </div>
        <div className={styles.formRow}>
          <label>Password</label>
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={handlePasswordChange}
          />
        </div>
        <button onClick={handleSubmitEmailAndPassword}>{label}</button>
      </div>
      <button onClick={onClickGoogle}>
        {inputType === AuthInputType.LOGIN
          ? "Login with Google"
          : "Sign up with Google"}
      </button>
    </div>
  );
});
