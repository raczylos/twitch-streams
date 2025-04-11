import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StreamsListComponent } from './streams-list/streams-list.component';
import { HttpClientModule } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { ReactiveFormsModule } from '@angular/forms';
import { ThumbnailPipe } from './thumbnail.pipe';
import { MatOptionModule } from '@angular/material/core';

@NgModule({
    declarations: [AppComponent, StreamsListComponent, ThumbnailPipe],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        HttpClientModule,
        MatCardModule,
        MatPaginatorModule,
        MatIconModule,
        MatButtonModule,
        MatProgressSpinnerModule,
        MatInputModule,
        MatSelectModule,
        MatFormFieldModule,
        MatAutocompleteModule,
        ReactiveFormsModule,
        MatOptionModule,
    ],
    providers: [],
    bootstrap: [AppComponent],
})
export class AppModule {}
