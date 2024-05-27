<script setup>
import Home from './components/Home.vue'
import Table from './components/Table.vue'
</script>

<template>
  <div className="w-full flex flex-col ">
     <header className="w-full h-[75px] flex items-center p-10 text-white bg-[#121214] border-b border-purple-800">
         <span>LOGO</span>
     </header>

    <main className="w-full flex flex-col mt-10 p-10">
      <Home title="MEUS PROJETOS" />

      <Table />
    </main>
  </div>
</template>

