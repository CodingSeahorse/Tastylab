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
                        <Tippy content="Profile">
                            <div className="container__elements__member" id="test"/>
                        </Tippy>
                    </Link>
                    <Link to="/home">
                        <Tippy content="Home">
                            <div className="container__elements__home"/>
                        </Tippy>
                    </Link>
                    <Tippy content="Search">
                        <div className="container__elements__search"/>
                    </Tippy>
                    <Tippy content="Wishlist">
                        <div className="container__elements__wishlist"/>
                    </Tippy>
                    <Tippy content="Lizzy">
                        <div className="container__elements__lizzy"/>
                    </Tippy>
                    <Tippy content="Write">
                        <div className="container__elements__write"/>
                    </Tippy>
                    <Tippy content="Option">
                        <div className="container__elements__option"/>
                    </Tippy>
                    <Tippy content="Logout">
                        <div className="container__elements__logout"
                             onClick={() => {
                                     AuthenticationService.logout()
                                     setTimeout(
                                         () => {history.push("/welcome/login")},
                                         1500)}}/>
                    </Tippy>
                </div>
            </nav>
        )
    }else {
        return (
            <nav className="container" id="no-user">
                <div className="container__elements">
                    <Link to="/welcome/login">
                        <Tippy content="Login">
                            <div className="container__elements__member" id="no-user-login">
                                <p>Login</p>
                            </div>
                        </Tippy>
                    </Link>
                    <Link to="/home">
                        <Tippy content="Home">
                            <div className="container__elements__home"/>
                        </Tippy>
                    </Link>
                    <Tippy content="Search">
                        <div className="container__elements__search"/>
                    </Tippy>
                    <Tippy content="Wishlist">
                        <div className="container__elements__wishlist"/>
                    </Tippy>
                    <Tippy content="Lizzy">
                        <div className="container__elements__lizzy"/>
                    </Tippy>
                    <Tippy content="Write">
                        <div className="container__elements__write"/>
                    </Tippy>
                    <Tippy content="Option">
                        <div className="container__elements__option"/>
                    </Tippy>
                </div>
            </nav>
        )
    }
}



export default Sidebar;