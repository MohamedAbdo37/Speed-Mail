<!-- eslint-disable vue/no-unused-components -->
<template>
  <router-link to="/">Home</router-link>
  <router-link to="/Compose">Compose</router-link>
  <router-link to="/Trash">Trash</router-link>
  <router-view/>

</template>
<script>
// eslint-disable-next-line camelcase
export default {
  name: 'App',
  components: {
  },
};
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;
    text-decoration: none;
    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
