<template>
    <div class="form-container">
      <h2>Adicionar Usuário</h2>
      <form @submit.prevent="addUser" class="form">

        
        <label for="name">Nome:</label>
        <input type="text" id="name" v-model="newUser.name" required>
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="newUser.email" required>
        <label for="bio">Biografia:</label>
        <textarea id="bio" v-model="newUser.bio" required></textarea>
        <label for="gender">Gênero:</label>
        <select id="gender" v-model="newUser.genderId" required>
            <option v-for="gender in genders" :key="gender.id" :value="gender.id">{{ gender.gender }}</option>
        </select>

        <label for="confirmed">Confirmado:</label>
        <div class="radio-buttons">
          <label><input type="radio" value="true" v-model="newUser.confirmed"> Sim</label>
          <label><input type="radio" value="false" v-model="newUser.confirmed"> Não</label>
        </div>
        <div class="button-container">
          <button type="submit">Salvar</button>
          <button type="button" @click="cancelAddUser">Cancelar</button>
        </div>
      </form>
      <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div> 

    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        newUser: { 
          name: '',
          email: '',
          bio: '',
          genderId: null,
          confirmed: false
        },
        genders: [],
        errorMessage: ''
      };
    },
    methods: {
    async addUser() {
        try {
            const formData = new FormData();
            formData.append('name', this.newUser.name);
            formData.append('email', this.newUser.email);
            formData.append('bio', this.newUser.bio);
            formData.append('genderId', this.newUser.genderId);
            formData.append('confirmed', this.newUser.confirmed);

            const response = await axios.post(process.env.VUE_APP_API_URL+'/user', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
            });
       
            this.newUser = {
            name: '',
            email: '',
            bio: '',
            genderId: null,
            confirmed: false
            };
        
            // Emitir um evento para notificar o componente pai que um novo usuário foi adicionado
            console.log(response.data);
            this.$emit('user-added', response.data);
            
      }catch (error){
        if (error.response && error.response.status === 400) {
            this.errorMessage = error.response.data; 
        } else {
            this.errorMessage + error.message; 
        }
        console.error('Erro ao adicionar usuário:', error);
      }
    },
      cancelAddUser() {
        this.$emit('cancel');
      },
      async getGenders() {
        try {
          const response = await axios.get('http://localhost:8080/gender');
          this.genders = response.data;
          console.log('Genders carregados:', this.genders);
        } catch (error) {
            this.errorMessage = 'Erro ao carregar gêneros: ' + error.message;
          console.error('Erro ao carregar Genders:', error);
        }
      }
    },
    mounted() {
      this.getGenders();
    }
  };
  </script>
  
  <style scoped>
  .form-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100%;
  }
  
  .form {
    max-width: 400px;
    width: 100%;
    margin: auto;
    display: flex;
    flex-direction: column;
  }
  
  .form label {
    margin-bottom: 5px;
  }
  
  .form input,
  .form textarea,
  .form select {
    width: 100%;
    margin-bottom: 10px;
    padding: 8px;
    box-sizing: border-box;
  }
  
  .radio-buttons {
    display: flex;
    margin-bottom: 10px;
    justify-content: center;
  }
  
  .radio-buttons label {
    margin-right: 10px;
  }
  .error-message{
    margin-top: 20px;
    color: red;
  }
  .button-container{
    display: flex;
    justify-content: space-around;
  }
  </style>
  