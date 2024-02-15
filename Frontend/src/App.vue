<template>
  <div id="App">
    <h1>Gestão de Usuarios</h1>
    <UserGestao/>
  </div>
</template>

<script>
import UserGestao from './components/UserGestao.vue'
export default {
  name: 'App',
  components: {
    UserGestao
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
