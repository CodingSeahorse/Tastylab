import React from "react";
import './input-form-fields.scss';

const FormInput = ({type, placeholder,name,value,onChange}) => (
    <React.Fragment>
        <input
            className="form-input"
            type={type}
            placeholder={placeholder}
            value={value}
            name={name}
            onChange={onChange}
        />
        <p className="form-input__subtitle">{placeholder}</p>
    </React.Fragment>
)

export default FormInput;