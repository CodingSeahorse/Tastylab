import React from "react";
import './sidebar.scss';

const Sidebar = () => (
    <nav className="container">
            <div className="container__elements">
                <div className="container__elements__member"/>
                <div className="container__elements__home"/>
                <div className="container__elements__search"/>
                <div className="container__elements__wishlist"/>
                <div className="container__elements__lizzy"/>
                <div className="container__elements__write"/>
                <div className="container__elements__option"/>
                <div className="container__elements__logout"/>
            </div>
    </nav>
)

export default Sidebar;