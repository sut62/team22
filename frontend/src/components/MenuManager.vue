<template>
  <div class="mainpage">

    <br>
    <v-container>
      <v-col cols = 4>
        <v-card>
          <v-col cols = 12 align="center">
            <h1>Menu Manager</h1>
            <br>
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
        </v-card>
      </v-col>

    </v-container>

  </div>
</template>

<script>
export default {

}
</script>
