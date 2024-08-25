@extends('backend.layouts.app')

@section('title') Edit {{ucfirst($moduleName)}} @endsection

@section('breadcrumbs')
    <x-backend-breadcrumbs>
        <x-backend-breadcrumb-item route='{{route("backend.{$moduleName}.index")}}' icon='fas fa-cogs'>
            {{ convertSnakeToTitle($moduleName) }}
        </x-backend-breadcrumb-item>

        <x-backend-breadcrumb-item type="active">Edit</x-backend-breadcrumb-item>
    </x-backend-breadcrumbs>
@endsection

@section('content')
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-8">
                    <h4 class="card-title mb-0">
                        <i class="fas fa-cogs"></i> {{ convertSnakeToTitle($moduleName) }}
                        <small class="text-muted"> Edit</small>
                    </h4>
                </div>
{{--                <div class="col-4">--}}
{{--                    <div class="btn-toolbar float-right" role="toolbar" aria-label="Toolbar with button groups">--}}
{{--                        <x-buttons.return-back>{{__('Return Back')}}</x-buttons.return-back>--}}
{{--                    </div>--}}
{{--                </div>--}}
            </div>
            <hr>
            {{$slot}}
        </div>
    </div>
@endsection
