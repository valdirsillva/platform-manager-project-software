<script setup>
import { ref, onMounted } from 'vue'
import { Menu, SquarePen } from 'lucide-vue-next'
import { api } from '../factories/factory-api'

const props = defineProps({
  title: {
    type: String,
    required: true
  }
})

const projectId = ref('')
const nameActivity = ref('')
const descriptionActivity = ref('')

const projects = ref([])
const activities = ref([])
const isModalOpen = ref(false)
const selectedProject = ref(null)
const isModalActivitOpen = ref(false)

const fetchProjects = async () => {
  try {
    const response = await api.get(`/projetos`)
    projects.value = response.data
  } catch (error) {
    console.error('Erro ao buscar dados da API:', error)
  }
}

const handleSubmitActivity = async () => {
  const id = projectId.value.id
  try {
     const response = await api.post(`/projetos/${id}/atividades`,{
        name: nameActivity.value,
        description: descriptionActivity.value,
    })

    if (response.status) {
      alert("Atividade cadastrado com sucesso")
    }
  } catch(error) {
    console.error(error.message)
  }
}

const openModal = (project) => {
  selectedProject.value = project
  isModalOpen.value = true
}

const closeModal = () => {
  isModalOpen.value = false
  selectedProject.value = null
}

const openModalCreateActivity = (project) => {
  selectedProject.value = project
  isModalActivitOpen.value = true
  projectId.value = project
}

const closeModalCreateActivity = () => {
  selectedProject.value = null
  isModalActivitOpen.value = false
}

function formatDataISOInPtBr(data){
    const [yyyy, mm, dd, hh, mi] = data.split(/[/:\-T]/);
    return [dd, mm, yyyy].join("-").concat(` ${[hh, mi].join(":")}`)
}

onMounted(() => {
  fetchProjects()
});

</script>

<template>
  <div className="w-full mt-10">
    <table className="w-12/12 table-fixed min-w-full text-left text-sm font-light text-surface dark:text-white">
      <thead  className="font-medium bg-purple-800">
        <tr>
          <!-- <th scope="col" className="px-6 py-2">#</th> -->
          <th scope="col" className="px-6 py-2">NOME</th>
          <th scope="col" className="px-6 py-2">DESCRIÇÃO</th>
          <th scope="col" className="px-6 py-2">STATUS</th>
          <th scope="col" className="px-6 py-2">DETALHES PROJETO</th>
          <th scope="col" className="px-6 py-2">AÇÕES</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="project in projects" :key="project.id" className="border-b border-neutral-200 dark:border-white/10">
            <!-- <td className="whitespace-nowrap px-6 py-4">{{ project.id }}</td> -->
            <td className="whitespace-nowrap px-6 py-4">{{ project.name }}</td>
            <td className="whitespace-nowrap px-6 py-4">{{ project.description }}</td>
            <td className="whitespace-nowrap px-6 py-4">{{ project.status }}</td>
            <td className="whitespace-nowrap px-6 py-4 flex justify-center">
              <button @click="openModal(project)">
                <Menu 
                  size="40" 
                  color="#41ec63"
                  className="cursor-pointer"
                />
              </button>
            </td>

            <td className="whitespace-nowrap px-6 py-4 ">
              <button type="button" role="button" @click="openModalCreateActivity(project)">
                <SquarePen
                   size="40" 
                   color="#41ec63"
                   className="cursor-pointer"
                />
               </button>
            </td>
          </tr>
      </tbody>
    </table>
  </div>

  <div v-if="isModalOpen" className="fixed inset-0 z-10 flex items-center justify-center bg-opacity-50">
    <div className="flex flex-col gap-5 bg-[#121218] p-6 rounded shadow-lg w-10/8 h-auto">
      <h2 className="text-2xl font-medium mb-4">Detalhes do Projeto</h2>
        <div className="flex flex-col">
          <ul className="flex flex-col gap-2">
              <li> <p><strong>Cliente:</strong> {{ selectedProject?.client.name }}</p></li>
              <li> <p><strong>E-mail:</strong> {{ selectedProject?.client.email }}</p></li>
              <li> <p><strong>Descrição:</strong> {{ selectedProject?.description }}</p></li>
              <li> <p><strong>Status:</strong> {{ selectedProject?.status }}</p></li>
          </ul>
        </div>
        <!-- Carregar lista de atividades aqui abaixo -->
        <div className="flex flex-col mt-5">
          <h2 className="text-2xl font-medium mb-4">Lista de Atividades</h2>
            <ul className="flex flex-col gap-2">
              <li v-for="activity in selectedProject.activities" :key="activity.id">
                <p>
                  <strong> {{ activity.name }} - </strong> {{ activity.description }} <br/>
                  <strong> Data criação: - </strong> {{ formatDataISOInPtBr(activity.dataInitial) }} 
                </p>
              </li>
            </ul>
          </div>

      <button @click="closeModal" className="w-20 mt-4 px-4 py-2 bg-rose-600 text-white rounded">
            Fechar
      </button>
    </div>
  </div>

  
  <div v-if="isModalActivitOpen" className="fixed inset-0 z-10 flex items-center justify-center bg-opacity-50">
    <div className="flex flex-col gap-5 bg-[#121218] p-6 rounded shadow-lg max-w-10/8 h-auto">
      <h2 className="text-2xl font-medium mb-6">Adicionar nova atividade</h2>
        <div className="flex flex-col gap-5">
           <form method="POST" @submit.prevent="handleSubmitActivity()">
              <div class="relative w-full min-w-[400px] mb-5 ">
                <input
                    className="peer w-full h-full bg-transparent text-white font-sans font-medium outline outline-0 focus:outline-0 transition-all placeholder-shown:border placeholder-shown:border-purple-500 placeholder-shown:border-t-purple-500 border focus:border-2  focus:border-t-transparent text-md px-3 py-3 rounded-[7px] border-purple-500 focus:border-purple-500"
                    placeholder=" "
                    type="text"
                  
                    v-model="projectId.id"
                  />
              </div>

              <div class="relative w-full min-w-[400px] mb-5 ">
               <input
                  className="peer w-full h-full bg-transparent text-white font-sans font-medium outline outline-0 focus:outline-0 disabled:bg-purple-500 disabled:border-0 transition-all placeholder-shown:border placeholder-shown:border-purple-500 placeholder-shown:border-t-purple-500 border focus:border-2 border-t-transparent focus:border-t-transparent text-md px-3 py-3 rounded-[7px] border-purple-500 focus:border-purple-500"
                  placeholder=" "
                  type="text"
                  v-model="nameActivity"
                />
              <label class="flex w-full h-full select-none pointer-events-none absolute left-0 font-normal !overflow-visible truncate peer-placeholder-shown:text-blue-gray-500 leading-tight peer-focus:leading-tight peer-disabled:text-transparent peer-disabled:peer-placeholder-shown:text-blue-gray-500 transition-all -top-1.5 peer-placeholder-shown:text-sm text-[11px] peer-focus:text-[11px] before:content[' '] before:block before:box-border before:w-2.5 before:h-1.5 before:mt-[6.5px] before:mr-1 peer-placeholder-shown:before:border-transparent before:rounded-tl-md before:border-t peer-focus:before:border-t-2 before:border-l peer-focus:before:border-l-2 before:pointer-events-none before:transition-all peer-disabled:before:border-transparent after:content[' '] after:block after:flex-grow after:box-border after:w-2.5 after:h-1.5 after:mt-[6.5px] after:ml-1 peer-placeholder-shown:after:border-transparent after:rounded-tr-md after:border-t peer-focus:after:border-t-2 after:border-r peer-focus:after:border-r-2 after:pointer-events-none after:transition-all peer-disabled:after:border-transparent peer-placeholder-shown:leading-[3.75] text-blue-gray-400 peer-focus:text-indigo-500 before:border-blue-gray-200 peer-focus:before:!border-indigo-500 after:border-blue-gray-200 peer-focus:after:!border-indigo-500">
                ATIVIDADE
              </label>
            </div>
             <div class="relative w-full min-w-[400px] mb-2 ">
               <textarea
                  className="peer w-full h-full bg-transparent text-white font-sans font-medium outline outline-0 focus:outline-0 disabled:bg-purple-500 disabled:border-0 transition-all placeholder-shown:border placeholder-shown:border-purple-500 placeholder-shown:border-t-purple-500 border focus:border-2 border-t-transparent focus:border-t-transparent text-md px-3 py-3 rounded-[7px] border-purple-500 focus:border-purple-500"
                  placeholder=" "
                  type="text"
                  rows="5"
                  v-model="descriptionActivity"
                />
              <label class="flex w-full h-full select-none pointer-events-none absolute left-0 font-normal !overflow-visible truncate peer-placeholder-shown:text-blue-gray-500 leading-tight peer-focus:leading-tight peer-disabled:text-transparent peer-disabled:peer-placeholder-shown:text-blue-gray-500 transition-all -top-1.5 peer-placeholder-shown:text-sm text-[11px] peer-focus:text-[11px] before:content[' '] before:block before:box-border before:w-2.5 before:h-1.5 before:mt-[6.5px] before:mr-1 peer-placeholder-shown:before:border-transparent before:rounded-tl-md before:border-t peer-focus:before:border-t-2 before:border-l peer-focus:before:border-l-2 before:pointer-events-none before:transition-all peer-disabled:before:border-transparent after:content[' '] after:block after:flex-grow after:box-border after:w-2.5 after:h-1.5 after:mt-[6.5px] after:ml-1 peer-placeholder-shown:after:border-transparent after:rounded-tr-md after:border-t peer-focus:after:border-t-2 after:border-r peer-focus:after:border-r-2 after:pointer-events-none after:transition-all peer-disabled:after:border-transparent peer-placeholder-shown:leading-[3.75] text-blue-gray-400 peer-focus:text-indigo-500 before:border-blue-gray-200 peer-focus:before:!border-indigo-500 after:border-blue-gray-200 peer-focus:after:!border-indigo-500">
                DESCRIÇÃO
              </label>
            </div>
             <div class="w-full flex gap-4 mt-2">
                <button type="submit" class="w-5/1o mt-4 px-4 py-2 bg-[#41ec63] text-white rounded"
                >
                  SALVAR
                </button>

              <button @click="closeModalCreateActivity()" className="w-20 mt-4 px-4 py-2 bg-rose-600 text-white rounded">
                Fechar
              </button>
              </div>
           </form>
        </div>
       </div> 
    </div>  



</template>

