import { Api } from "@/services/Api.js"

class ApiFactory {
    static create() {
        return new Api()
    }
}

const api = ApiFactory.create()
export { api }