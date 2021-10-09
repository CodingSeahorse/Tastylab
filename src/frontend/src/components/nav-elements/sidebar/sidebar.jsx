import React from "react";
import {
    Link,
    useHistory} from "react-router-dom";

import './sidebar.scss';
import 'tippy.js/dist/tippy.css';

import Tippy from "@tippyjs/react";
import AuthenticationService from "../../../services/AuthenticationService";

const Sidebar = ({isLoggedIn}) => {
    const history = useHistory()
    if(isLoggedIn){
        return (
            <nav className="container">
                <div className="container__elements">
                    <Link to="/welcome/login"> {/*TODO:REPLACE WITH PROFILE PAGE*/}
                        <Tippy themeinteractive={true} content="Profile" style={{color:'green'}}>
                            <div className="container__elements__member" id="test"/>
                        </Tippy>
                    </Link>
                    <Link to="/home">
                        <Tippy themeinteractive={true} content="Home">
                            <div className="container__elements__home"/>
                        </Tippy>
                    </Link>
                    <Tippy themeinteractive={true} content="Search">
                        <div className="container__elements__search"/>
                    </Tippy>
                    <Tippy themeinteractive={true} content="Wishlist">
                        <div className="container__elements__wishlist"/>
                    </Tippy>
                    <Tippy themeinteractive={true} content="Lizzy">
                        <div className="container__elements__lizzy"/>
                    </Tippy>
                    <Tippy themeinteractive={true} content="Write">
                        <div className="container__elements__write"/>
                    </Tippy>
                    <Tippy themeinteractive={true} content="Option">
                        <div className="container__elements__option"/>
                    </Tippy>
                    <Tippy themeinteractive={true} content="Logout">
                        <div className="container__elements__logout"
                             onClick={() => {
                                     AuthenticationService.logout()
                                     setTimeout(
                                         () => {history.push("/welcome/login")},
                                         2000)}}/>
                    </Tippy>
                </div>
            </nav>
        )
    }else {
        return (
            <nav className="container" id="no-user">
                <div className="container__elements">
                    <Link to="/welcome/login">
                        <Tippy themeinteractive={true} content="Login">
                            <div className="container__elements__member" id="no-user-login">
                                <p>Login</p>
                            </div>
                        </Tippy>
                    </Link>
                    <Link to="/home">
                        <Tippy themeinteractive={true} content="Home">
                            <div className="container__elements__home"/>
                        </Tippy>
                    </Link>
                    <Tippy themeinteractive={true} content="Search">
                        <div className="container__elements__search"/>
                    </Tippy>
                    <Tippy themeinteractive={true} content="Wishlist">
                        <div className="container__elements__wishlist"/>
                    </Tippy>
                    <Tippy themeinteractive={true} content="Lizzy">
                        <div className="container__elements__lizzy"/>
                    </Tippy>
                    <Tippy themeinteractive={true} content="Write">
                        <div className="container__elements__write"/>
                    </Tippy>
                    <Tippy themeinteractive={true} content="Option">
                        <div className="container__elements__option"/>
                    </Tippy>
                </div>
            </nav>
        )
    }
}



export default Sidebar;