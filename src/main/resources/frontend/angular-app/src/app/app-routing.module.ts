import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateUserComponent } from "./create-user/create-user.component";
import { ListUsersComponent } from "./list-users/list-users.component";
import { ViewUserComponent } from "./view-user/view-user.component";

const routes: Routes = [
  {path: 'users', component: ListUsersComponent},
  {path: 'create-user', component: CreateUserComponent},
  {path: '', redirectTo: 'users', pathMatch: 'full'},
  {path: 'user-details/:id', component: ViewUserComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
