<template>
  <div class="form-container">
    <h2>{{ editing ? 'Editar Usuário' : 'Adicionar Usuário' }}</h2>
    <form @submit.prevent="submitForm" class="form">
      <label for="name">Nome:</label>
      <input type="text" id="name" v-model="formData.name" required>
      <label for="email">Email:</label>
      <input type="email" id="email" v-model="formData.email" required>
      <label for="bio">Biografia:</label>
      <textarea rows="10" id="bio" v-model="formData.bio" required ></textarea>
      <label for="gender">Gênero:</label>
      <!-- Os select a baixo estão repetidos porque tive problemas ao marcar no caso de atualizar o user -->
      <select id="gender" v-model="formData.genderId" v-if="editing" required>
        <option selected :key="this.selected.id" :value="this.selected.id">{{this.selected.gender}}</option>
        <option v-for="gender in filter" :key="gender.id" :value="gender.id"> {{ gender.gender }}</option>
      </select>
      <select id="gender" v-model="formData.genderId" v-else required>
        <option v-for="gender in genders" :key="gender.id" :value="gender.id"> {{ gender.gender }}</option>
      </select>

      <label for="confirmed">Confirmado:</label>
      <div class="radio-buttons">
        <label><input type="radio" value="true" v-model="formData.confirmed"> Sim</label>
        <label><input type="radio" value="false" v-model="formData.confirmed"> Não</label>
      </div>
      <div class="button-container">
        <button type="submit" class="primary-button">{{ editing ? 'Salvar' : 'Adicionar' }}</button>
        <button type="button" @click="cancel" class="secondary-button">{{ editing ? 'Cancelar' : 'Limpar' }}</button>
      </div>
    </form>
    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div> 
  </div>
</template>

<script>
import axios from 'axios';

export default {
  props: {
    userData: Object, 
    editing: Boolean   
  },
  data() {
    return {
      formData: {
        name: '',
        email: '',
        bio: '',
        genderId: null,
        confirmed: false
      },
      genders: [],
      errorMessage: '', 
      filter: [], 
      selected: {}
    };
  },
  methods: {
    async submitForm() {
      try {
        let formData = new FormData();
        formData.append('name', this.formData.name);
        formData.append('email', this.formData.email);
        formData.append('bio', this.formData.bio);
        formData.append('genderId', this.formData.genderId);
        formData.append('confirmed', this.formData.confirmed);
        
      

        if (this.editing) {
          if(this.userData.id ){
            formData.append('id', this.userData.id);
          }
          const url = process.env.VUE_APP_API_URL + `/user`;
          await axios.put(url, formData);
          this.$emit('user-updated');
        } else {
          const response = await axios.post(process.env.VUE_APP_API_URL + '/user', formData);
          this.$emit('user-added', response.data);
        }
        this.resetForm();
      } catch (error) {
        if (error.response && error.response.status === 400) {
          this.errorMessage = error.response.data; 
        } else {
          this.errorMessage = 'Erro ao processar a solicitação.';
        }
        console.error('Erro ao processar a solicitação:', error);
      }
    },
    cancel() {
      this.resetForm();
      this.$emit('cancel');
    },
    resetForm() {
     
      this.formData = {
        name: '',
        email: '',
        bio: '',
        genderId: null,
        confirmed: false
      };
      this.errorMessage = '';
    },
    async getGenders() {
      try {
        const response = await axios.get(process.env.VUE_APP_API_URL + '/gender');
        this.genders = response.data;
        if(this.editing){
          console.log('Genders carregados:', this.genders);
           this.selected = this.genders.filter(g => g.id == this.userData.gender.id)[0]
           this.filter = this.genders.filter(g => g.id != this.userData.gender.id)
           this.formData.genderId  = this.selected.id;
        }
      } catch (error) {
        this.errorMessage = 'Erro ao carregar gêneros: ' + error.message;
        console.error('Erro ao carregar Genders:', error);
      }
    }
  },
  mounted() {
    console.log(this.userData)
    this.getGenders();
    if (this.editing && this.userData) {
      this.formData = { ...this.userData };
      
    }
    
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

.primary-button {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 4px;
}

.secondary-button {
  background-color: #f44336;
  border: none;
  color: white;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 4px;
}

.primary-button:hover {
  background-color: #45a049;
}

.secondary-button:hover {
  background-color: #db2d20;
}
</style>
