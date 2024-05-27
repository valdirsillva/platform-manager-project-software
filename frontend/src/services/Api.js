import { AxiosConfig } from "./AxiosConfig.js";

/**
 * Implementa Princípio da Responsabilidade Única (SRP)
 */
export class Api {
    httpRequest

    constructor() {
        this.httpRequest = AxiosConfig.getInstance();
    }

    async get(endpoint) {
        return await this.httpRequest.get(endpoint)
    }

    async post(endpoint, data) {
        return await this.httpRequest.post(endpoint, data)
    }

    async getById(endpoint, id) {
        return await this.httpRequest.get(endpoint, id)
    }
}