import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ScoreComponent} from './score/score.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'score',
    pathMatch: 'full',
  },
  {
    path: 'score',
    component: ScoreComponent,
    data: {
      title: 'Home'
    },
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
