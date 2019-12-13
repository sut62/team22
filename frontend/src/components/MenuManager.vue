<template>
  <div class="mainpage">
    <v-toolbar>
      <v-toolbar-title>Menu Manager - Restaurant</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-title>User : Kaigitti</v-toolbar-title>
      <v-btn depressed small>Logout</v-btn>
    </v-toolbar>

    <div class="outside">
      <br>
      <v-container>
        <v-col cols = 3>
          <v-text-field
          v-model="firstname"
          :rules="nameRules"
          :counter="20"
          label="Menu Name"
          required
          ></v-text-field>
          <v-text-field
          v-model="firstname"
          :rules="nameRules"
          :counter="20"
          label="Menu Price"
          required
          ></v-text-field>
          <br>
          <v-combobox
          v-model="select"
          :items="items"
          label="Menu Type"
          multiple
          outlined
          dense
          ></v-combobox>
          <br>
          <v-combobox
          v-model="select"
          :items="items"
          label="Menu Ingredient"
          multiple
          outlined
          dense
          ></v-combobox>
          <br>
          <v-combobox
          v-model="select"
          :items="items"
          label="Menu Category "
          multiple
          outlined
          dense
          ></v-combobox>
          <br>
          <v-col>
            <v-btn small >Add Menu</v-btn>
          </v-col>
        </v-col>

      </v-container>
    </div>

  </div>
</template>

<script>
export default {

}
</script>
