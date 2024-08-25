@extends('backend.layouts.app')

@section('title') Edit Admins @endsection

@section('breadcrumbs')
<x-backend-breadcrumbs>
    <x-backend-breadcrumb-item route='{{route("backend.admins.index")}}' icon='c-icon cil-people' >
        Admins
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
                    <i class="c-icon cil-people"></i> {{ __('labels.backend.admins.edit.title') }}
                    <small class="text-muted">{{ __('labels.backend.admins.edit.action') }} </small>
                </h4>
            </div>
        </div>
        <hr>

        <div class="row mt-4">
            <div class="col">
                {{ html()->modelForm($admin, 'PATCH', route('backend.admins.update', $admin->operator_id))->class('form-horizontal')->open() }}

                    <div class="form-group row">
                        {{ html()->label(__('labels.backend.admins.fields.name'))->class('col-sm-2 form-control-label')->for('display_name') }}
                        <div class="col-sm-10">
                            {{ html()->text('display_name')
                                    ->class('form-control')
                                    ->placeholder(__('labels.backend.admins.fields.name'))
                                    ->attribute('maxlength', )
                                    ->required() }}
                        </div>
                    </div>

                    <div class="form-group row">
                        {{ html()->label(__('labels.backend.admins.fields.username'))->class('col-sm-2 form-control-label')->for('username') }}

                        <div class="col-sm-10">
                            {{ html()->text('username')
                                ->class('form-control')
                                ->placeholder(__('labels.backend.admins.fields.username'))
                                ->attribute('maxlength', 45)
                                ->required() }}
                        </div>
                    </div>

                    <div class="form-group row">
                        {{ html()->label(__('labels.backend.admins.fields.status'))->class('col-6 col-sm-2 form-control-label')->for('status') }}

                        <div class="col-6 col-sm-10">
                            {{ html()->select('status')
                                    ->child($statuses->map(function ($status) use ($admin) {
                                        return html()->option($status, $status)
                                        ->selectedIf($admin->status === $status);
                                    }))
                                    ->class('form-control')
                                    ->required() }}
                        </div>
                    </div>

                    <div class="form-group row">
                        {{ html()->label('Role')->class('col-sm-2 form-control-label')->for('role_id') }}
                        <div class="col-6 col-sm-10">
                            {{ html()->select('roles')
                                    ->child($roles->map(function ($role) use ($admin) {
                                        return html()->option($role->name, $role->name)
                                        ->selectedIf($admin->roles->first() && $admin->roles->first()->name === $role->name);
                                    }))
                                    ->class('form-control')
                                    ->required() }}
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group">
                                <x-buttons.edit moduleName="admins"> {{__('Update')}}</x-buttons.edit>
                                <x-buttons.cancel> {{__('Cancel')}}</x-buttons.cancel>
                            </div>
                        </div>
                    </div>
                {{ html()->closeModelForm() }}
            </div>
        </div>
    </div>
</div>

@endsection
