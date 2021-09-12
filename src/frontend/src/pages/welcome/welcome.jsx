import React, {useState} from "react";
import Login from "./login/login";
import Registration from "./registration/registration";

const Welcome = () => {
    const[login,setLogin] = useState({ activeLoginContent: true});

    const loginContentLoad = () => (
       <Login/>
    )

    const registrationContentLoad = () => (
        <Registration/>
    )

    const switchContent = () => {
        if(login.activeLoginContent === true){
            loginContentLoad();
        }else {
            registrationContentLoad()
        }
    }

}
