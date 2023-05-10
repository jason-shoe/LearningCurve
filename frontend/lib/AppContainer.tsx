import React from "react";
import styles from "../styles/AppContainer.module.scss";

export const AppContainer = React.memo(function AppContainerFn({
  children,
}: {
  children: React.ReactNode;
}) {
  return <div className={styles.container}>{children}</div>;
});
