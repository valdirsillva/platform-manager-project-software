<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { api } from '../factories/factory-api.js'

defineProps({
  title: {
    type: String,
    required: true
  }
})
const clients = ref([])

const clientId = ref('')
const name = ref('')
const phoneNumber = ref('')
const email = ref('')
// project
const nameProject = ref('')
const description = ref('')
const status = ref('PENDENTE')
const createtAt = ref('')

const isModalClientOpen = ref(false)
const isModalProjectOpen = ref(false)
const openModalClient = () => isModalClientOpen.value = true;
const closeModalClient = () => isModalClientOpen.value = false;
const openModalProject = () => isModalProjectOpen.value = true;
const closeModalProject = () => isModalProjectOpen.value = false;

const handleClientSubmit = async () => {
   try {
    const response = await axios.post("http://localhost:8080/api/clientes",{
        name: name.value,
        phoneNumber: phoneNumber.value,
        email: email.value
    })

    if (response.status) {
      alert("Cliente cadastrado com sucesso")

      name.value = ''
      phoneNumber.value = ''
      email.value = ''
    }
   } catch(error) {
     console.error(error.message)
   }
}

const handleProjectSubmit = async () => {
   try {
    const response = await api.post(`/projetos`,{
        clientId: clientId.value,
        name: nameProject.value,
        description: description.value,
        status: status.value,
        // dataInitial: "2024-05-25T14:30:00",
        // dataEnd: null
    })

    if (response.status) {
      alert("Projeto cadastrado com sucesso")
    }
   } catch(error) {
     console.error(error.message)
   }
}

const getClients = async () => {
   try {
    const response = await api.get(`/clientes`)
    clients.value = response.data
  } catch (error) {
    console.error('Erro ao buscar clientes:', error)
  }
}

onMounted(() => {
  getClients()
})
</script>

<template>
  <div className="w-full flex flex-row justify-between">
    <h1 className="text-purple-500 text-3xl font-medium">{{ title }}</h1>
    <div className="flex gap-5">
       <button type="button" 
          className="bg-transparent hover:bg-[#41ec63] text-white font-semibold hover:text-white py-2 px-4 border border-[#41ec63] hover:border-transparent rounded"
        @click="openModalClient()">
         Cadastrar cliente
        </button>
       <button type="button"
         className="bg-transparent hover:bg-[#41ec63] text-white font-semibold hover:text-white py-2 px-4 border border-[#41ec63] hover:border-transparent rounded"
         
         @click="openModalProject()" >Cadastrar projeto</button>
    </div>
  </div>

  <div v-if="isModalClientOpen" className="fixed inset-0 z-10 flex items-center justify-center bg-opacity-50">
    <div className="flex flex-col gap-5 bg-[#121218] p-6 rounded shadow-lg max-w-10/8 h-auto">
      <h2 className="text-2xl font-medium mb-4">Cadastrar cliente</h2>
        <div className="flex flex-col gap-5">
           <form method="POST" @submit.prevent="handleClientSubmit">
              <div class="relative w-full min-w-[400px] mb-10 ">
               <input
                  className="peer w-full h-full bg-transparent text-white font-sans font-medium outline outline-0 focus:outline-0 disabled:bg-purple-500 disabled:border-0 transition-all placeholder-shown:border placeholder-shown:border-purple-500 placeholder-shown:border-t-purple-500 border focus:border-2 border-t-transparent focus:border-t-transparent text-md px-3 py-3 rounded-[7px] border-purple-500 focus:border-purple-500"
                  placeholder=" "
                  type="text"
                  v-model="name"
                />
              <label class="flex w-full h-full select-none pointer-events-none absolute left-0 font-normal !overflow-visible truncate peer-placeholder-shown:text-blue-gray-500 leading-tight peer-focus:leading-tight peer-disabled:text-transparent peer-disabled:peer-placeholder-shown:text-blue-gray-500 transition-all -top-1.5 peer-placeholder-shown:text-sm text-[11px] peer-focus:text-[11px] before:content[' '] before:block before:box-border before:w-2.5 before:h-1.5 before:mt-[6.5px] before:mr-1 peer-placeholder-shown:before:border-transparent before:rounded-tl-md before:border-t peer-focus:before:border-t-2 before:border-l peer-focus:before:border-l-2 before:pointer-events-none before:transition-all peer-disabled:before:border-transparent after:content[' '] after:block after:flex-grow after:box-border after:w-2.5 after:h-1.5 after:mt-[6.5px] after:ml-1 peer-placeholder-shown:after:border-transparent after:rounded-tr-md after:border-t peer-focus:after:border-t-2 after:border-r peer-focus:after:border-r-2 after:pointer-events-none after:transition-all peer-disabled:after:border-transparent peer-placeholder-shown:leading-[3.75] text-blue-gray-400 peer-focus:text-indigo-500 before:border-blue-gray-200 peer-focus:before:!border-indigo-500 after:border-blue-gray-200 peer-focus:after:!border-indigo-500">
                NOME
              </label>
            </div>

            <div class="relative w-full min-w-[400px] ">
               <input
                className="peer w-full h-full bg-transparent text-white font-sans font-medium outline outline-0 focus:outline-0 disabled:bg-purple-500 disabled:border-0 transition-all placeholder-shown:border placeholder-shown:border-purple-500 placeholder-shown:border-t-purple-500 border focus:border-2 border-t-transparent focus:border-t-transparent text-md px-3 py-3 rounded-[7px] border-purple-500 focus:border-purple-500"
                placeholder=" " 
                maxLength="15"
                type="text"
                v-model="phoneNumber"
               >
              <label class="flex w-full h-full select-none pointer-events-none absolute left-0 font-normal !overflow-visible truncate peer-placeholder-shown:text-blue-gray-500 leading-tight peer-focus:leading-tight peer-disabled:text-transparent peer-disabled:peer-placeholder-shown:text-blue-gray-500 transition-all -top-1.5 peer-placeholder-shown:text-sm text-[11px] peer-focus:text-[11px] before:content[' '] before:block before:box-border before:w-2.5 before:h-1.5 before:mt-[6.5px] before:mr-1 peer-placeholder-shown:before:border-transparent before:rounded-tl-md before:border-t peer-focus:before:border-t-2 before:border-l peer-focus:before:border-l-2 before:pointer-events-none before:transition-all peer-disabled:before:border-transparent after:content[' '] after:block after:flex-grow after:box-border after:w-2.5 after:h-1.5 after:mt-[6.5px] after:ml-1 peer-placeholder-shown:after:border-transparent after:rounded-tr-md after:border-t peer-focus:after:border-t-2 after:border-r peer-focus:after:border-r-2 after:pointer-events-none after:transition-all peer-disabled:after:border-transparent peer-placeholder-shown:leading-[3.75] text-blue-gray-400 peer-focus:text-indigo-500 before:border-blue-gray-200 peer-focus:before:!border-indigo-500 after:border-blue-gray-200 peer-focus:after:!border-indigo-500">
                TELEFONE
              </label>
            </div>

            <div class="relative w-full min-w-[400px] mt-10">
               <input
                className="peer w-full h-full bg-transparent placeholder:f:bg-red-400 text-white font-sans font-medium outline outline-0 focus:outline-0 disabled:bg-purple-500 disabled:border-0 transition-all placeholder-shown:border placeholder-shown:border-purple-500 placeholder-shown:border-t-purple-500 border focus:border-2 border-t-transparent focus:border-t-transparent text-md px-3 py-3 rounded-[7px] border-purple-500 focus:border-purple-500"
                placeholder=" " 
                maxLength="80"
                type="email"
                v-model="email"

               />
              <label class="flex w-full h-full select-none pointer-events-none absolute left-0 font-normal !overflow-visible truncate peer-placeholder-shown:text-blue-gray-500 leading-tight peer-focus:leading-tight peer-disabled:text-transparent peer-disabled:peer-placeholder-shown:text-blue-gray-500 transition-all -top-1.5 peer-placeholder-shown:text-sm text-[11px] peer-focus:text-[11px] before:content[' '] before:block before:box-border before:w-2.5 before:h-1.5 before:mt-[6.5px] before:mr-1 peer-placeholder-shown:before:border-transparent before:rounded-tl-md before:border-t peer-focus:before:border-t-2 before:border-l peer-focus:before:border-l-2 before:pointer-events-none before:transition-all peer-disabled:before:border-transparent after:content[' '] after:block after:flex-grow after:box-border after:w-2.5 after:h-1.5 after:mt-[6.5px] after:ml-1 peer-placeholder-shown:after:border-transparent after:rounded-tr-md after:border-t peer-focus:after:border-t-2 after:border-r peer-focus:after:border-r-2 after:pointer-events-none after:transition-all peer-disabled:after:border-transparent peer-placeholder-shown:leading-[3.75] text-blue-gray-400 peer-focus:text-indigo-500 before:border-blue-gray-200 peer-focus:before:!border-indigo-500 after:border-blue-gray-200 peer-focus:after:!border-indigo-500">
                E-MAIL
              </label>
            </div>

            <div class="w-full flex gap-4 mt-8">
              <button type="submit" class="w-5/12 mt-4 px-4 py-2 bg-[#41ec63] text-white rounded"
              @click="handleClientSubmit"
              >
                SALVAR
              </button>

              <button @click="closeModalClient" 
                class="w-20 mt-4 px-4 py-2 bg-rose-600 text-white rounded">
                FECHAR
              </button>
            </div>
           </form>
        </div>
    </div>
  </div>

  <div v-if="isModalProjectOpen" className="fixed inset-0 z-10 flex items-center justify-center bg-opacity-50">
    <div className="flex flex-col gap-5 bg-[#121218] p-6 rounded shadow-lg max-w-10/8 h-auto">
      <h2 className="text-2xl font-medium mb-4">Cadastrar projeto</h2>
        <div className="flex flex-col gap-5">
           <form method="POST" @submit.prevent="handleProjectSubmit">
             <div class="relative w-full min-w-[400px] mb-10 ">
               <select v-model="clientId" class="peer w-full h-full bg-transparent text-black font-sans font-medium outline outline-0 focus:outline-0 disabled:bg-purple-500 disabled:border-0 transition-all placeholder-shown:border placeholder-shown:border-purple-500 placeholder-shown:border-t-purple-500 border focus:border-2 border-t-transparent focus:border-t-transparent text-md px-3 py-3 rounded-[7px] border-purple-500 focus:border-purple-500">
                    <option v-for="client in clients" :key="client.id" :value="client.id">{{ client.name }}</option>
                </select>
            </div>

              <div class="relative w-full min-w-[400px] mb-10 ">
               <input
                  className="peer w-full h-full bg-transparent text-white font-sans font-medium outline outline-0 focus:outline-0 disabled:bg-purple-500 disabled:border-0 transition-all placeholder-shown:border placeholder-shown:border-purple-500 placeholder-shown:border-t-purple-500 border focus:border-2 border-t-transparent focus:border-t-transparent text-md px-3 py-3 rounded-[7px] border-purple-500 focus:border-purple-500"
                  placeholder=" "
                  type="text"
                  v-model="nameProject"
                />
              <label class="flex w-full h-full select-none pointer-events-none absolute left-0 font-normal !overflow-visible truncate peer-placeholder-shown:text-blue-gray-500 leading-tight peer-focus:leading-tight peer-disabled:text-transparent peer-disabled:peer-placeholder-shown:text-blue-gray-500 transition-all -top-1.5 peer-placeholder-shown:text-sm text-[11px] peer-focus:text-[11px] before:content[' '] before:block before:box-border before:w-2.5 before:h-1.5 before:mt-[6.5px] before:mr-1 peer-placeholder-shown:before:border-transparent before:rounded-tl-md before:border-t peer-focus:before:border-t-2 before:border-l peer-focus:before:border-l-2 before:pointer-events-none before:transition-all peer-disabled:before:border-transparent after:content[' '] after:block after:flex-grow after:box-border after:w-2.5 after:h-1.5 after:mt-[6.5px] after:ml-1 peer-placeholder-shown:after:border-transparent after:rounded-tr-md after:border-t peer-focus:after:border-t-2 after:border-r peer-focus:after:border-r-2 after:pointer-events-none after:transition-all peer-disabled:after:border-transparent peer-placeholder-shown:leading-[3.75] text-blue-gray-400 peer-focus:text-indigo-500 before:border-blue-gray-200 peer-focus:before:!border-indigo-500 after:border-blue-gray-200 peer-focus:after:!border-indigo-500">
                NOME DO PROJETO
              </label>
            </div>

            <div class="relative w-full min-w-[400px] ">
               <textarea
                className="peer w-full h-full bg-transparent text-white font-sans font-medium outline outline-0 focus:outline-0 disabled:bg-purple-500 disabled:border-0 transition-all placeholder-shown:border placeholder-shown:border-purple-500 placeholder-shown:border-t-purple-500 border focus:border-2 border-t-transparent focus:border-t-transparent text-md px-3 py-3 rounded-[7px] border-purple-500 focus:border-purple-500"
                placeholder=" " 
                type="text"
                v-model="description"
               >
               </textarea>
              <label class="flex w-full h-full select-none pointer-events-none absolute left-0 font-normal !overflow-visible truncate peer-placeholder-shown:text-blue-gray-500 leading-tight peer-focus:leading-tight peer-disabled:text-transparent peer-disabled:peer-placeholder-shown:text-blue-gray-500 transition-all -top-1.5 peer-placeholder-shown:text-sm text-[11px] peer-focus:text-[11px] before:content[' '] before:block before:box-border before:w-2.5 before:h-1.5 before:mt-[6.5px] before:mr-1 peer-placeholder-shown:before:border-transparent before:rounded-tl-md before:border-t peer-focus:before:border-t-2 before:border-l peer-focus:before:border-l-2 before:pointer-events-none before:transition-all peer-disabled:before:border-transparent after:content[' '] after:block after:flex-grow after:box-border after:w-2.5 after:h-1.5 after:mt-[6.5px] after:ml-1 peer-placeholder-shown:after:border-transparent after:rounded-tr-md after:border-t peer-focus:after:border-t-2 after:border-r peer-focus:after:border-r-2 after:pointer-events-none after:transition-all peer-disabled:after:border-transparent peer-placeholder-shown:leading-[3.75] text-blue-gray-400 peer-focus:text-indigo-500 before:border-blue-gray-200 peer-focus:before:!border-indigo-500 after:border-blue-gray-200 peer-focus:after:!border-indigo-500">
                DESCRIÇÃO
              </label>
            </div>

            <div class="w-full flex gap-4 mt-8">
              <button type="submit" class="w-5/12 mt-4 px-4 py-2 bg-[#41ec63] text-white rounded"
              @click="handleProjectSubmit"
              >
                SALVAR
              </button>

              <button @click="closeModalProject" 
                class="w-20 mt-4 px-4 py-2 bg-rose-600 text-white rounded">
                FECHAR
              </button>
            </div>
           </form>
        </div>
    </div>
  </div>

</template>
