import React from "react";
import './sidebar.scss';
import {Link, useHistory} from "react-router-dom";

import AuthenticationService from "../../../services/AuthenticationService";

const Sidebar = ({isLoggedIn}) => {
    const history = useHistory()
    if(isLoggedIn){
        return (
            <nav className="container">
                <div className="container__elements">
                    <Link to="/welcome/login">
                        <div className="container__elements__member"/>
                    </Link>
                    <Link to="/home">
                        <div className="container__elements__home"/>
                    </Link>
                    <div className="container__elements__search"/>
                    <div className="container__elements__wishlist"/>
                    <div className="container__elements__lizzy"/>
                    <div className="container__elements__write"/>
                    <div className="container__elements__option"/>
                    <div className="container__elements__logout"
                         onClick={() => {
                                 AuthenticationService.logout()
                                 setTimeout(
                                     () => {history.push("/welcome/login")},
                                     2000)}}/>
                </div>
            </nav>
        )
    }else {
        return (
            <nav className="container">
                <div className="container__elements">
                    <Link to="/welcome/login">
                        <div className="container__elements__member">
                            <p>Login</p>
                        </div>
                    </Link>
                    <Link to="/home">
                        <div className="container__elements__home"/>
                    </Link>
                    <div className="container__elements__search"/>
                    <div className="container__elements__wishlist"/>
                    <div className="container__elements__lizzy"/>
                    <div className="container__elements__write"/>
                    <div className="container__elements__option"/>
                </div>
            </nav>
        )
    }
}



export default Sidebar;