import React from "react";
import './page-turn-arrow.scss';
import {Link} from "react-router-dom";

const PageSwitchArrow = ({route}) => (
    <Link to={route}>
        <input
            className="nav-arrow"
            type="button"
        />
    </Link>
)

export default PageSwitchArrow;