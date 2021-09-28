import React from "react";
import './sidebar.scss';
import {Link} from "react-router-dom";

const Sidebar = () => (
    <nav className="container">
            <div className="container__elements">
                <div className="container__elements__member"/>
                <Link to="/home">
                    <div className="container__elements__home"/>
                </Link>
                <div className="container__elements__search"/>
                <div className="container__elements__wishlist"/>
                <div className="container__elements__lizzy"/>
                <div className="container__elements__write"/>
                <div className="container__elements__option"/>
                <Link to="/welcome/login">
                    <div className="container__elements__logout"/>
                </Link>
            </div>
    </nav>
)

export default Sidebar;