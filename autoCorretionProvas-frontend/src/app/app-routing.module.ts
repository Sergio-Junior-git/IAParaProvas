import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GabaritoAvaliarComponent } from './components/gabarito-avaliar/gabarito-avaliar.component';
import { GabaritoCiadorComponent } from './components/gabarito-ciador/gabarito-ciador.component';

const routes: Routes = [
  {path: 'criar', component : GabaritoCiadorComponent},
  {path: 'avaliar', component : GabaritoAvaliarComponent},
  {path: '', redirectTo: '/criar', pathMatch: 'full'},
  {path: '**', redirectTo: '/criar' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
