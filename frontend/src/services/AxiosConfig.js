import axios from "axios";

export class AxiosConfig {
    static instance;
    constructor() {
        // eslint-disable-next-line 
    }

    static getInstance() {
        if (!AxiosConfig.instance) {
            AxiosConfig.instance = axios.create({
                baseURL: import.meta.env.VITE_APP_API_HOST ?? '',
                headers: {
                    'Content-Type': 'application/json',
                    // 'Authorization': `Bearer ${token}`
                }
            })
        }

        return AxiosConfig.instance;
    }
}