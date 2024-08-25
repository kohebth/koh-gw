@extends ('backend.layouts.app')

@section('title') List Admins @endsection

@section('breadcrumbs')
<x-backend-breadcrumbs>
    <x-backend-breadcrumb-item type="active" icon='c-icon cil-people'>Admins</x-backend-breadcrumb-item>
</x-backend-breadcrumbs>
@endsection

@section('content')
<div class="card">
    <div class="card-body">
        <div class="row">
            <div class="col">
                <h4 class="card-title mb-0">
                    <i class="c-icon cil-people"></i> {{ __('labels.backend.admins.index.title') }}
                    <small class="text-muted">{{ __('labels.backend.admins.index.action') }} </small>
                </h4>
            </div>

            <div class="col-5">
                <div class="float-right">
                    <x-buttons.create moduleName="admins" route='{{ route("backend.admins.create") }}'>
                        {{__('Create')}}
                    </x-buttons.create>
                </div>
            </div>
        </div>
        <livewire:admin.index />
    </div>
    <div class="card-footer">

    </div>
</div>

@endsection
