import React from "react";
import axios from "axios";

import {API_URL} from "../Constants";

class SignupService {

    signUpRequest(postedForm){
        const registrationRequest = {
            username:postedForm.username,
            password:postedForm.password,
            firstName: postedForm.firstName,
            lastName: postedForm.lastName,
            email: postedForm.email,
            age: postedForm.age,
            gender: postedForm.gender
        }

        return axios.post(`${API_URL}/api/welcome/signup`,{
            ...registrationRequest
        })
    }
}

export default new SignupService();