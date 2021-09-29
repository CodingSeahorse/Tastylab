import React from "react";

import './mybutton.scss';

const Mybutton = ({onClick}) => (
    <input
        className="my-button"
        type="submit"
        onClick={onClick}
    />
)

export default Mybutton;