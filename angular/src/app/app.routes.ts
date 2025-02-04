import {Routes} from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {UserComponent} from "./components/user/user.component";
import {ArtistComponent} from "./components/artist/artist.component";
import {RedirectComponent} from "./components/common/redirect/redirect.component";
import {loginGuard} from "./Security/AuthGuard/login-guard";
import {authGuard} from "./Security/AuthGuard/auth-guard";
import {MusicComponent} from "./components/music/music.component";

export const routes: Routes = [
  {  path: '', redirectTo: 'login', pathMatch: 'full'},
  { path: 'login', component: LoginComponent, canActivate: [loginGuard]},
  { path: 'register', component: RegisterComponent },
  { path: 'dashboard' , component: DashboardComponent, canActivate: [authGuard] },
  { path: 'user', component: UserComponent, canActivate: [authGuard],
    data: {
    roles: ['super_admin'],
    } },
  { path: 'artist', component: ArtistComponent, canActivate: [authGuard],
    data: {
      roles: ['super_admin', 'artist_manager']
    }},
  { path: 'artist/:id', component: MusicComponent, canActivate: [authGuard],
    data: {
      roles: ['super_admin', 'artist_manager', 'artist']
    }},
  { path: '**', component: RedirectComponent },
];
