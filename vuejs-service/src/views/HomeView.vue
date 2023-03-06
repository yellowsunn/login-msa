<template>
  <div class="home">
    <OAuth2Login/>
  </div>
</template>

<script>
// @ is an alias to /src
import OAuth2Login from "@/components/OAuth2Login.vue";

export default {
  name: 'HomeView',
  components: {
    OAuth2Login,
  }
}
</script>
