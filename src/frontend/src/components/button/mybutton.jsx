import React from "react";
import './mybutton.scss';

const Mybutton = ({value},{onClick}) => (
    <input
        className="my-button"
        type="button"
        value={value}
        onClick={onClick}
    />
)

export default Mybutton;