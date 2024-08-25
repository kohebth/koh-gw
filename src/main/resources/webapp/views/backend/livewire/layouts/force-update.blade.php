@extends('backend.layouts.app')

@section('title') Force To Update {{ucfirst($moduleName)}} @endsection

@section('breadcrumbs')
    <x-backend-breadcrumbs>
        <x-backend-breadcrumb-item route='{{route("backend.{$moduleName}.index")}}' icon='fas fa-cogs'>
            {{ convertSnakeToTitle($moduleName) }}
        </x-backend-breadcrumb-item>

        <x-backend-breadcrumb-item type="active">Force To Update</x-backend-breadcrumb-item>
    </x-backend-breadcrumbs>
@endsection

@section('content')
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-8">
                    <h4 class="card-title mb-0">
                        <i class="fas fa-cogs"></i> {{ convertSnakeToTitle($moduleName) }}
                        <small class="text-muted">Force To Update</small>
                    </h4>
                </div>
            </div>
            <hr>
            {{$slot}}
        </div>
    </div>
@endsection
