import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StreamsListComponent } from './streams-list/streams-list.component';

const routes: Routes = [{ path: '', component: StreamsListComponent }];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {}
