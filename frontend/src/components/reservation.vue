<template>
  <v-container class="mt-12 ml-12">
    <v-text-field
      v-model.number="reservation.customer"      
      label="Member ID"  
      prepend-icon="mdi-human-male-female"
      :rules="[(v) => !!v || 'Item is required']"
      required
    ></v-text-field>
		<v-btn color="success"
      @click="customerchk"
    >Search</v-btn>
		<v-menu max-width="290" >
      <template v-slot:activator="{ on }">
        <v-text-field
        
        readonly
        label="Date"
        prepend-icon="mdi-calendar-range"
        :value="formattedDate"
        v-on="on"
        :rules="[(v) => !!v || 'Item is required']"
        required
      ></v-text-field>
      </template>
      <v-date-picker v-model="date" ></v-date-picker>
    </v-menu>
    <v-row class="ml-0">
        <v-menu
         
          v-model="menu1"
          :close-on-content-click="false"
          :nudge-right="40"
          lazy
          transition="scale-transition"
          offset-y
          max-width="290x"
          max-height="290x"
        >
          <template v-slot:activator="{on}"
          >
            <v-text-field
              v-model="reservation.time"
              v-on="on"
              label="Pick Time"
              prepend-icon="access_time"
              readlonly
              id="id"
              :rules="[(v) => !!v || 'Item is required']"
              required
            ></v-text-field>
            
          </template>
          <v-time-picker
              v-if="menu1"
              v-model="reservation.time"
              full-width
              format="24hr"
              @click:minute="$ref.menu.save(time)"
            ></v-time-picker>
        </v-menu>
     </v-row>
    <v-select
      label="Select Table"
      v-model="reservation.table"
      :items="table"
      item-text="id"
      item-value="id"
      prepend-icon="mdi-table-chair"
      return-object
      :rules="[(v) => !!v || 'Item is required']"
      required
    >
    </v-select>
    <v-select
      label="Select Number of Seats"
      v-model="reservation.seats"
      :items="seat"
      item-text="id"
      item-value="id"
      prepend-icon="mdi-seat"
      :rules="[(v) => !!v || 'Item is required']"
      required
     
    >
    </v-select>
    <v-select
      :items="services"
      v-model="reservation.service"
      label="Select Service"
      item-text="serviceName"
      item-value="id"
      prepend-icon="mdi-room-service"
      :rules="[(v) => !!v || 'Item is required']"
      required
    ></v-select>
    <v-btn color="success"
      @click="save"
      
    >SAVE 
    <v-icon>mdi-floppy</v-icon>
    </v-btn>
  </v-container>
</template>

<script>
import format from 'date-fns/format'
import parselISO from 'date-fns/parseISO'
import https from '../plugins/https'
export default {
  name: 'reservation',

  data: () => ({
        menu1:false,
        date:null,
        service:null,
        reservation:{
          time:'',
          table:'',
          seats:'',
          customer:'',
          service:'',
        },
        reservations:[],
        services:[],
        findmember:false,
        table:[],
        seat:[],
  }),
  computed:{
    formattedDate(){
      return this.date ? format(parselISO(this.date), 'do MMM YYY') : ''
    },
    seats(){
      return this.reservation.table
    },
    
    
    
  },
  methods:{
    customerchk(){
      https.get("/members/"+this.reservation.customer).then( doc =>{
              if(doc.data!=null){
                alert("Member found.")
              }
              else{
                alert("Member not found.")
                this.reservation.customer = null
              }
      })
    },
  
    save(){
       
        https.post("/reservationses/"+this.reservation.customer+"/"+this.reservation.table.id+"/"+this.reservation.service+"/"+this.date+"/"+this.reservation.time+"/"+this.reservation.seats)
        .then(()=> {
            alert("Reservation complete")
            
        })
        .catch(e =>{
            alert("Reservation fail "+ e.name+" "+e.message)
        })
    },
    getTables(){
       https.get("/tableses").then( doc =>{

                      this.table=doc.data
                  
                   
      })
      .catch(e =>{
          alert(e.name+" "+e.message)
      })
    },
    getService(){
        https.get("/serviceses").then( doc =>{
              
                      
                  this.services=doc.data
               
                
      })
      .catch( e =>{
        alert(e.name+" "+e.message)
      })
    },
    getReservations(){
      https.get("/reservationses/"+this.reservation.table.id+"/"+this.date+"/"+this.reservation.time).then( doc => {
        this.reservations.push(doc)
      })
      .catch(e => {
        alert(e.name+" "+e.message)
      })
    },
    
  },
  mounted(){
    this.getTables()
    this.getService()
   
  },
  watch:{
    seats(){
      let mxSeat = this.reservation.table.seats
      for(let nseat = 1;nseat <= mxSeat;nseat++){
        this.seat.push({id:nseat})
      }
    },
    
  }  
}
</script>
