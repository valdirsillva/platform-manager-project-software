<script setup>
import axios from 'axios'
import { ref, onMounted } from 'vue'
import { Menu } from 'lucide-vue-next'

const props = defineProps({
  title: {
    type: String,
    required: true
  }
})

const projects = ref([])
const activities = ref([])
const isModalOpen = ref(false)
const selectedProject = ref(null)

const fetchProjects = async () => {
  const url = "http://localhost:8080/api/projetos"
  try {
    const response = await axios.get(url)
    projects.value = response.data
  } catch (error) {
    console.error('Erro ao buscar dados da API:', error)
  }
}

// const fetchActivities = async (projectId) => {
//   const url = `http://localhost:8080/api/projetos/${projectId}/atividades`
//   try {
//     const response = await axios.get(url)
//     activities.value = response.data
//   } catch (error) {
//     console.error('Erro ao buscar atividades:', error)
//   }
// }

const openModal = (project) => {
  selectedProject.value = project
  isModalOpen.value = true
  // fetchActivities(project.id)
};

const closeModal = () => {
  isModalOpen.value = false
  selectedProject.value = null
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
          </tr>
      </tbody>
    </table>

  </div>

  <div v-if="isModalOpen" className="fixed inset-0 z-10 flex items-center justify-center bg-opacity-50">
    <div className="flex flex-col gap-5 bg-[#121218] p-6 rounded shadow-lg max-w-10/8 h-auto">
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
          <h2 className="text-2xl font-medium mb-4">Atividades</h2>
          <ul className="flex flex-col gap-2">
            <li v-for="activity in selectedProject.activities" :key="activity.id">
              <p>
                <strong> {{ activity.name }} - </strong> {{ activity.description }} <br/>
                <strong> Data criação: - </strong> {{ formatDataISOInPtBr(activity.dataInitial) }} 
              </p>
            </li>
          </ul>
        </div>

      <button @click="closeModal" 
        class="w-20 mt-4 px-4 py-2 bg-rose-600 text-white rounded">
        Fechar
      </button>
    </div>
  </div>

</template>

