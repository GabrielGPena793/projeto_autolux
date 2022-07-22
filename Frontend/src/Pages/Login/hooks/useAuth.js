import { useContext } from "react";
import { AuthContext } from "../contextLogin/auth";

const useAuth = () => {
    const context = useContext(AuthContext);

    return context;
};

export default useAuth;